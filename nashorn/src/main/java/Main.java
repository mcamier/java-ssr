import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws ScriptException, FileNotFoundException {

        System.setProperty("nashorn.args", "--language=es6");
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        engine.eval(read("nashorn-polyfill.js"));
        engine.eval(read("jvm-npm.js"));
        engine.eval(read("runtime.js"));
        engine.eval(read("app.js"));
        Object result = engine.eval(read("main.js"));

        System.out.println("HelloWorld");
    }

    private static InputStreamReader read(String filename) {
        ClassLoader classLoader = Main.class.getClassLoader();
        return new InputStreamReader(classLoader.getResourceAsStream(filename));
    }
}
