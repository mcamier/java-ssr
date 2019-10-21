import org.mozilla.javascript.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RhinoMain {

    public static void main(String[] args) throws ScriptException, IOException {
        // initialize context
        Context ctxt = Context.enter();
        ctxt.setLanguageVersion(Context.VERSION_ES6);
        ScriptableObject scope = ctxt.initStandardObjects();

        // evaluate scripts
        ctxt.evaluateReader(scope, read("rhino-polyfill.js"), "rhino-polyfill.js",1, null);
        ctxt.evaluateReader(scope, read("jvm-npm.js"), "jvm-npm.js",1, null);
        ctxt.evaluateReader(scope, read("runtime.js"), "runtime.js",1, null);
        ctxt.evaluateReader(scope, read("app.js"), "app.js",1, null);

        ctxt.evaluateReader(scope, read("main.js"), "main.js",0 , null);

        Report report = new Report("project name", "Campaign name");
        Object jsObj = Context.javaToJS(report, scope);

        Object fObj = scope.get("foobar", scope);
        Function f = (Function) fObj;
        Object result = f.call(ctxt, scope, null, new Object[]{jsObj});

        if (result instanceof Wrapper) {
             Object test = ((Wrapper) result).unwrap();
        }

        Context.exit();
    }

    private static InputStreamReader read(String filename) {
        ClassLoader classLoader = RhinoMain.class.getClassLoader();
        return new InputStreamReader(classLoader.getResourceAsStream(filename));
    }
}
