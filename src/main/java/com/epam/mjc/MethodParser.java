package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        var modifAndName = signatureString.substring(0, signatureString.indexOf("("));
        var keys = modifAndName.split(" ");
        String modifier = null;
        String returnType;
        String methodName;
        if (keys.length == 2) {
            methodName = keys[1];
            returnType = keys[0];
        } else {
            methodName = keys[2];
            modifier = keys[0];
            returnType = keys[1];
        }
        var arguments = signatureString.substring(signatureString.indexOf("(")+1,
                signatureString.lastIndexOf(")"));

        var st = new StringTokenizer(arguments, ",");
        List<MethodSignature.Argument> args = new ArrayList<>();

        if (st.hasMoreElements()) {
            while (st.hasMoreElements()) {
                var argement = ((String) st.nextElement()).strip().split(" ");
                args.add(new MethodSignature.Argument(argement[0], argement[1]));
            }
        }


        MethodSignature res = new MethodSignature(methodName, args);
        res.setReturnType(returnType);
        res.setAccessModifier(modifier);
        return res;
    }
}
