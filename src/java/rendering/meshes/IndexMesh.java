package rendering.meshes;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class IndexMesh implements Mesh {
	
	protected final int vertexCount, vertexArrayID, vertexBufferID, indexBufferID;
	
	public IndexMesh(float[] vertices, int[] indices) {
		
		vertexCount = indices.length;
		
		this.vertexArrayID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArrayID);
		
		this.vertexBufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER,
				vertices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);

		this.indexBufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBufferID);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,
				indices, GL15.GL_STATIC_DRAW);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	@Override
	public void render() {
		
		GL30.glBindVertexArray(vertexArrayID);
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	@Override
	public void release() throws Exception {
		GL15.glDeleteBuffers(vertexBufferID);
		GL15.glDeleteBuffers(indexBufferID);
		GL30.glDeleteVertexArrays(vertexArrayID);
	}
}
