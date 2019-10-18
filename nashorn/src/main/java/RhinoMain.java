import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RhinoMain {

    public static void main(String[] args) throws ScriptException, IOException {

        Context ctxt = Context.enter();
        ctxt.setLanguageVersion(Context.VERSION_ES6);

        ScriptableObject scope = ctxt.initStandardObjects();

        ctxt.evaluateReader(scope, read("rhino-polyfill.js"), "rhino-polyfill.js",1, null);
        ctxt.evaluateReader(scope, read("jvm-npm.js"), "jvm-npm.js",1, null);
        ctxt.evaluateReader(scope, read("runtime.js"), "runtime.js",1, null);
        ctxt.evaluateReader(scope, read("app.js"), "app.js",1, null);
        Object test = ctxt.evaluateReader(scope, read("main.js"), "main.js",0 , null);

        /*String script = "const toto = 212;" +
                "toto;" +
                "print(2123);" +
                "print('trop cool');" +
                "print(toto);";
        Object test = ctxt.evaluateString(scope, script, "test", 0, null);
*/
        //System.out.println(test);
        Context.exit();
    }

    private static InputStreamReader read(String filename) {
        ClassLoader classLoader = RhinoMain.class.getClassLoader();
        return new InputStreamReader(classLoader.getResourceAsStream(filename));
    }
}
