package com.github.test_pkg.project;

import org.ros.RosCore;
import org.ros.node.DefaultNodeMainExecutor;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMain;
import org.ros.node.NodeMainExecutor;

import java.net.URI;

public class ROSTest {
  //private static RosCore rosCore;
  private static DefaultNodeMainExecutor executor = (DefaultNodeMainExecutor) DefaultNodeMainExecutor.newDefault();

  public static void main(String[] args)
  {
    /*13111 is the ROS port. if you are launching ros files 
      from roscpp or rospy make sure to specify the argument '-p 13111'
      when you run the launch file.*/
    //rosCore = RosCore.newPublic(11311);
    //rosCore.start();

    try {
      //rosCore.awaitStart();

      URI masterUri = new URI("http://192.168.0.105:11311");

      Talker talker = new Talker();
      Listener listener = new Listener();

      execute("Listener", listener, masterUri);
      execute("Talker", talker, masterUri);

      Thread.sleep(2000);

      executor.shutdown();
    }
    catch (Throwable e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private static void execute(String name, NodeMain node, URI masterUri)
  {
    System.out.println("Starting " + name + " node...");
    NodeConfiguration config = NodeConfiguration.newPrivate();
    //config.setMasterUri(rosCore.getUri());
      config.setMasterUri(masterUri);
    config.setNodeName(name);
    executor.execute(node, config);
  }
}
