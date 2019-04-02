package com.github.test_pkg.project;

import org.ros.RosCore;
import org.ros.node.DefaultNodeMainExecutor;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMain;
import org.ros.node.NodeMainExecutor;

import java.net.URI;

public class ROSTest {
  private static RosCore rosCore;
  private static DefaultNodeMainExecutor executor = (DefaultNodeMainExecutor) DefaultNodeMainExecutor.newDefault();

  public static void main(String[] args)
  {
    /*13111 is the ROS port. if you are launching ros files 
      from roscpp or rospy make sure to specify the argument '-p 13111'
      when you run the launch file.*/
//    rosCore = RosCore.newPublic(11311); 
//    rosCore.start();

    try {
//      rosCore.awaitStart();


      Talker talker = new Talker();
      Listener listener = new Listener();

      execute("Listener", listener);
      execute("Talker", talker);

      Thread.sleep(3000);

  //    executor.shutdownNodeMain(listener);

    //  Thread.sleep(2000);

//      executor.shutdownNodeMain(talker);
      executor.shutdown();
    }
    catch (Throwable e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private static void execute(String name, NodeMain node)
  {
    System.out.println("Starting " + name + " node...");
    NodeConfiguration config = NodeConfiguration.newPrivate();
    URI u = null;
    try {
        u = new URI("http://Sullust:11311");
    } catch (Exception e) {
        System.out.println("Error creating URI");        
    }
    config.setMasterUri(u);//rosCore.getUri());
    config.setNodeName(name);
    executor.execute(node, config);
  }
}
