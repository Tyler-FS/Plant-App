package plantappspring.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Scanner;

@Component
public class InteractiveCommandLineRunner implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public InteractiveCommandLineRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command (format: className.methodName(param1,param2,...), or 'exit' to quit):");
            String command = scanner.nextLine();
            if ("exit".equalsIgnoreCase(command)) {
                System.out.println("Exiting...");
                break;
            }
            try {
                // Split the command into class name and method part
                String[] parts = command.split("\\.");
                String className = parts[0];
                String methodPart = parts[1];
                String methodName = methodPart.split("\\(")[0];
                String paramsPart = methodPart.split("\\(")[1].replace(")", "");
                String[] paramValues = paramsPart.isEmpty() ? new String[0] : paramsPart.split(",");

                // Get the bean from the application context
                Object bean = applicationContext.getBean(className);
                Method[] methods = bean.getClass().getMethods();
                for (Method method : methods) {
                    // Find the method with the matching name and parameter count
                    if (method.getName().equals(methodName) && method.getParameterCount() == paramValues.length) {
                        Object[] params = new Object[paramValues.length];
                        for (int i = 0; i < paramValues.length; i++) {
                            // Convert the parameter values to the appropriate types
                            params[i] = convertParam(method.getParameterTypes()[i], paramValues[i]);
                        }
                        // Invoke the method and print the result
                        Object result = method.invoke(bean, params);
                        System.out.println("Result: " + result);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Converts a string parameter value to the appropriate type.
     *
     * @param paramType the type of the parameter
     * @param paramValue the string value of the parameter
     * @return the converted parameter value
     */
    private Object convertParam(Class<?> paramType, String paramValue) {
        if (paramType == int.class || paramType == Integer.class) {
            return Integer.parseInt(paramValue);
        } else if (paramType == long.class || paramType == Long.class) {
            return Long.parseLong(paramValue);
        } else if (paramType == boolean.class || paramType == Boolean.class) {
            return Boolean.parseBoolean(paramValue);
        } else {
            return paramValue;
        }
    }
}