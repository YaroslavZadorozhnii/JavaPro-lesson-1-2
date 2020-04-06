package com.gmail.docfordja;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Main {
    public static class Sumator {

        @MyAnotation(a = 61, b = 14)
        public static int sum(int a, int b) {
            return (a + b);
        }
    }

        public static class Multiplicator {

            @MyAnotation(a = 80, b = 4)
            public static int multiplication(int a, int b){
                return (a * b);
            }
        }

    public static class Divisionator {

        @MyAnotation(a = 22, b = 13)
        public static int division(int a, int b){
            return (a / b);
        }

        
    }

    public static void main (String[] arg){
        runer(Sumator.class,Multiplicator.class,Divisionator.class);

    }
    public static void runer(Class<?>... cls){
        
            for (int i = 0 ; i < cls.length; i++) {
                Method[] methods = cls[i].getDeclaredMethods();
                for (int j = 0 ; j < methods.length ; j++) {
                    if(methods[j].isAnnotationPresent(MyAnotation.class)){
                        try {
                        	MyAnotation myA = methods[j].getAnnotation(MyAnotation.class);
							int one = (int)  methods[j].getAnnotation(MyAnotation.class).a();
							int two = (int)  methods[j].getAnnotation(MyAnotation.class).b();
                        	System.out.println(methods[j].getName() + " ( " + "@a: " + one + " , " + "@b: " +
							two + " ) " + " = " + (int)methods[j].invoke(null, one, two));
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }

            }
    }
    
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyAnotation {
        int a() default (1);
        int b() default (2);
    }
    
}