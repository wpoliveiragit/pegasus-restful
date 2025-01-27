package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.crud.app.controller.ItemController;
import br.com.projeto.crud.app.controller.UserController;

public class Main {

	public static void main(String[] args) {
		
		
		
		String[] controllerPaths = getControllerPaths(ItemController.class, UserController.class);
		Arrays.stream(controllerPaths).forEach(System.out::println);
		
		
		
	}

	public static String[] getControllerPaths(Class<?>... controllerClazzes) {

		List<String> paths = new ArrayList<>();

		for (int i = 0; i < controllerClazzes.length; i++) {
			Class<?> controllerClass = controllerClazzes[i];
			if (controllerClass.isAnnotationPresent(RequestMapping.class)) {
				String path = Arrays.stream(controllerClass.getAnnotation(RequestMapping.class).value()).findFirst().orElse(null);
				if(path == null) {
					continue;
				}
				paths.add("/" + path+ "/**");
			}
		}

		return paths.toArray(new String[0]);
	}
}
