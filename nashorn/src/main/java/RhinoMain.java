import org.mozilla.javascript.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RhinoMain {

    private Context ctxt;
    private ScriptableObject scope;

    public RhinoMain() {
        ctxt = Context.enter();
        ctxt.setLanguageVersion(Context.VERSION_ES6);
        scope = ctxt.initStandardObjects();

        // evaluate scripts
        try {
            ctxt.evaluateReader(scope, read("rhino-polyfill.js"), "rhino-polyfill.js",1, null);
            //ctxt.evaluateReader(scope, read("r.js"), "r.js",1, null);
            ctxt.evaluateReader(scope, read("jvm-npm.js"), "r.js",1, null);
            ctxt.evaluateReader(scope, read("bundle.js"), "bundle.js",1, null);
        } catch (IOException e) {
            throw new RuntimeException("RhinoMain failed to start");
        }
    }

    public String evaluate(Report report) {
        Object jsObj = Context.javaToJS(report, scope);

        Object fObj = scope.get("foobar", scope);
        Function f = (Function) fObj;
        Object result = f.call(ctxt, scope, null, new Object[]{jsObj});

        if (result instanceof Wrapper) {
            result = ((Wrapper) result).unwrap();
        }

        return (String) result;
    }

    private static InputStreamReader read(String filename) {
        ClassLoader classLoader = RhinoMain.class.getClassLoader();
        return new InputStreamReader(classLoader.getResourceAsStream(filename));
    }
}
