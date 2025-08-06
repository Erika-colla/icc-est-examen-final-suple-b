-injars lib/validaciones-temp.jar
-outjars lib/validadores-evaluacion.jar

-libraryjars /Library/Java/JavaVirtualMachines/temurin-18.jdk/Contents/Home/lib/modules
-libraryjars lib/junit-jupiter-api-5.7.0.jar
-libraryjars lib/hamcrest-core-1.3.jar
-libraryjars lib/apiguardian-api-1.1.0.jar

-dontwarn java.**
-dontwarn javax.**
-dontwarn org.junit.**
-ignorewarnings

-dontoptimize
-dontpreverify
-overloadaggressively

# Mantener métodos públicos de ValidacionesMaquina
-keep public class validaciones.ValidacionesMaquina {
    public static <methods>;
}

# Mantener Maquina sin ofuscar
-keep public class models.Maquina {
    public *;
}
