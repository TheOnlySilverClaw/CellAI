package main;


import java.time.Instant;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.GLFW.*;

public class Start {

	public static void main(String[] args) throws Exception {
		
		Logger log = LoggerFactory.getLogger(Start.class);
		log.info("Starting at '{}'", Instant.now());
		
		if(glfwInit()) {
			long window = GLFW.glfwCreateWindow(400, 600, "CellAI", NULL, NULL);
			glfwMakeContextCurrent(window);
			glfwShowWindow(window);
			while(!glfwWindowShouldClose(window)) {
				glfwPollEvents();
			}
			glfwTerminate();
			log.info("GLFW Termianted succesfully.");
		}
		log.info("Shutting down at '{}'", Instant.now());
	}
}
