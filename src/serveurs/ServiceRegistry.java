package serveurs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Vector;

import services.Service;

public class ServiceRegistry {
	// cette classe est un registre de services
	// partagée en concurrence par les clients et les "ajouteurs" de services,
	// un Vector pour cette gestion est pratique

	static {
		servicesClasses = new Vector<Class<? extends Service>>();
		urlcl = new CustomURLClassLoader(null);
	}
	
	private static List<Class<? extends Service>> servicesClasses;
	private static CustomURLClassLoader urlcl;
	
	public static void addService(String cl, String login) throws Exception{
		Class< ? extends Service> c = null;
		try {
			c = (Class< ? extends Service>) urlcl.loadClass(login + "." + cl);
		} catch (ClassNotFoundException e) {
			throw new Exception("La classe est introuvable");
		}
		addService(c);
	}
	
// ajoute une classe de service après contrôle de la norme BLTi
	private static void addService(Class<? extends Service> classe) throws IllegalArgumentException{
		isBLTiFriendly(classe);
		// si conforme, ajout au vector
		servicesClasses.add(classe);
	}
	
// renvoie la classe de service (numService -1)	
	public static Class<? extends Service> getServiceClass(int numService) {
		return servicesClasses.get(numService - 1); 
	}
	
// liste les activités présentes
	public static String toStringue() {
		String result = "Activités présentes : ##";
		synchronized(servicesClasses){
			for (int i = 1; i <= servicesClasses.size(); i++){
				try {
					result += i + ">" + getServiceClass(i).getSimpleName()+ " : " + (String) getServiceClass(i).getMethod("toStringue").invoke(null) + "##";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static void isBLTiFriendly(Class<? extends Service> classe){
		// vérifier la conformité par introspection
		// si non conforme --> exception avec message clair
		if(!(Arrays.asList(classe.getInterfaces()).contains(Service.class)))
			throw new IllegalArgumentException("La classe n'implémente pas blti.Service.");
		if(!Modifier.isPublic(classe.getModifiers()))
			throw new IllegalArgumentException("La classe n'est pas publique.");
		try{
			Constructor<?> c = classe.getConstructor(Socket.class);
			if(!Modifier.isPublic(c.getModifiers()))
				throw new NoSuchMethodException();
		}catch(NoSuchMethodException e){
			throw new IllegalArgumentException("La classe ne contient pas de constructeur public(Socket).");
		}
		for(int i = 0; i < classe.getDeclaredFields().length; i++){
			Field f = classe.getDeclaredFields()[i];
			if(f.getType().equals(Socket.class) && Modifier.isPrivate(f.getModifiers()) && Modifier.isFinal(f.getModifiers()))
				break;
			if(i == classe.getFields().length - 1)
				throw new IllegalArgumentException("La classe ne contient pas d'attribut Socket private final.");
		}
		for(int i = 0; i < classe.getDeclaredMethods().length; i++){
			Method m = classe.getDeclaredMethods()[i];
			if(m.getName().equals("toStringue") && m.getReturnType().equals(String.class) && Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers()))
				break;
			if(i == classe.getDeclaredMethods().length - 1)
				throw new IllegalArgumentException("La classe ne contient pas de méthode toStringue public static.");
		}
	}
	
	public static void refreshServices(String loginProp) throws Exception{
		List<Integer> errors = null;
		for (int i = 0; i < servicesClasses.size(); i++){
			if(servicesClasses.get(i).getPackage().getName().equals(loginProp)){
				Class<? extends Service> c;
				try {
					c = (Class<? extends Service>) 
							urlcl.loadClass(loginProp + "." + servicesClasses.get(i).getName());
					isBLTiFriendly(c);
				} catch (Exception e) {
					if(errors == null)
						errors = new Vector<Integer>();
					errors.add(i);
					continue;
				}
				servicesClasses.set(i, c);
			}
		}
		if(errors != null){
			String errorLog = "Les classes suivantes n'ont pas été rafraichies : \n";
			for(Integer j : errors){
				errorLog += servicesClasses.get(j).getName() + "\n";
			}
			throw new Exception(errorLog);
		}
	}

	public static void majService(String res, String login) throws Exception {
		for (int i = 0; i < servicesClasses.size(); i++){
			if(servicesClasses.get(i).getName().equals(res)){
				Class<? extends Service> c;
				c = (Class<? extends Service>) 
						urlcl.loadClass(login + "." + servicesClasses.get(i).getName());
				isBLTiFriendly(c);
				servicesClasses.set(i, c);
				return;
			}
		}
		throw new Exception("Service introuvable");
	}
	
}
