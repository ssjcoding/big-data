package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2020/1/6 12:23 AM,
 */
public class HDFSClient{
	private FileSystem fs;

	@Before
	public void before() throws IOException, InterruptedException{
		fs = FileSystem.get(URI.create("hdfs://localhost:9000"), new Configuration(), "tony");
		System.out.println("Before ......");
	}

	@Test
	public void put() throws IOException{
		fs.copyFromLocalFile(new Path("/Users/tony/dump.rdb"), new Path("/test/"));
	}

	@Test
	public void get() throws IOException{
		fs.copyToLocalFile(new Path("/test/dump.rdb"), new Path("/Users/tony/Downloads/tmp/"));
	}

	@Test
	public void rename() throws IOException{
		fs.rename(new Path("/test"), new Path("/test2"));
	}

	@Test
	public void delete() throws IOException{
		boolean delete = fs.delete(new Path("/test2"), true);
		if(delete){
			System.out.println("删除成功");
		}
	}

	@Test
	public void append() throws IOException{
		FSDataOutputStream fsDataOutputStream= fs.append(new Path("/wcinput/wc.input"), 1024);
		FileInputStream fileInputStream = new FileInputStream("/Users/tony/Downloads/tmp/test.txt");
		IOUtils.copyBytes(fileInputStream, fsDataOutputStream, 1024, true);
	}

	@Test
	public void ls() throws IOException{
		FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
		for(FileStatus fileStatus : fileStatuses){
			System.out.println(fileStatus.getPath().toString() + " is file :" + fileStatus.isFile());
			System.out.println(fileStatus.getPermission() + " " + fileStatus.getAccessTime() + " " + fileStatus.getLen());
		}
	}

	@Test
	public void lsFiles() throws IOException{
		RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/"), true);
		while(files.hasNext()){
			LocatedFileStatus locatedFileStatus = files.next();
			System.out.println(locatedFileStatus.getPath());
			BlockLocation[] blockLocations = locatedFileStatus.getBlockLocations();
			for(BlockLocation blockLocation : blockLocations){
				String[] hosts = blockLocation.getHosts();
				System.out.println("块在");
				for(String host : hosts){
					System.out.println(host + " ");
				}
			}
		}
	}

	@Test
	public void mv_t() throws IOException{
		Trash trash = new Trash(new Configuration());
		trash.moveToTrash(new Path(""));
	}

	@After
	public void after() throws IOException{
		fs.close();
		System.out.println("After ......");
	}
}
