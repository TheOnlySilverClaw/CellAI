package rendering;

import org.lwjgl.opengl.GL20;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShaderProgram {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private final int id;
	private final int vertexShader;
	private final int fragmentShader;
	
	public ShaderProgram(String vertexShaderSource,
			String fragmentShaderSource) throws Exception {
		
		this.id = GL20.glCreateProgram();
		this.vertexShader = createShader(
				vertexShaderSource, GL20.GL_VERTEX_SHADER);
		this.fragmentShader = createShader(
				fragmentShaderSource, GL20.GL_FRAGMENT_SHADER);
	}
	
	private int createShader(String source, int type) throws Exception {
	
		int shaderID = GL20.glCreateShader(type);
		if(shaderID == 0) {
			log.debug("Invalid shader.");
		}
		GL20.glShaderSource(shaderID, source);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0) {
			throw new Exception("Error in shader compilation: "
					+ GL20.glGetShaderInfoLog(shaderID));
		}
		GL20.glAttachShader(id, shaderID);
		return id;
	}
	
	public void link() throws Exception {
		GL20.glLinkProgram(id);
		if(GL20.glGetProgrami(id, GL20.GL_LINK_STATUS) == 0) {
			throw new Exception(GL20.glGetProgramInfoLog(id));
		}
		GL20.glValidateProgram(id);
		if(GL20.glGetProgrami(id, GL20.GL_VALIDATE_STATUS) == 0) {
			log.debug("Validation warning: {}", GL20.glGetProgramInfoLog(id));
		}
	}
	
	public void bind() {
		GL20.glUseProgram(id);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public void destroy() {
		unbind();
		GL20.glDetachShader(id, vertexShader);
		GL20.glDetachShader(id, fragmentShader);
		GL20.glDeleteShader(vertexShader);
		GL20.glDeleteShader(fragmentShader);
		GL20.glDeleteProgram(id);
	}
}
