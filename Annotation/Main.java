
import java.lang.reflect.Method;
public class Main {
    public static void main(String[] args) {
        Class<Calcul> classALire = Calcul.class;
        for(Method methode : classALire.getDeclaredMethods()){
            if(methode.isAnnotationPresent(Auteur.class)){
                Auteur annotation = methode.getAnnotation((Auteur.class));
                System.out.println("La méthode '" + methode.getName() + "' a été écrite par : " + annotation.nom());
            }
        }
    }
}
