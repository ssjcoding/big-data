package interview;

import java.nio.ByteBuffer;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/25 1:26 AM,
 */
public class DirectBufferMemoryDemo{
	public static void main(String[] args){
		System.out.println((sun.misc.VM.maxDirectMemory()/(double)(1024*1024)));
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);
	}
}
