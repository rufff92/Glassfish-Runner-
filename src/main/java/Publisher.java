import org.glassfish.embeddable.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Publisher {
    public static void main(String[] args) throws GlassFishException, IOException {

        GlassFishProperties properties=new GlassFishProperties();
        properties.setPort("http-listener", 8080);
        GlassFish glassFish= GlassFishRuntime.bootstrap().newGlassFish(properties);
        glassFish.start();
        Deployer deployer=glassFish.getDeployer();

        File war = new File("C:\\javaprojects\\karafProject\\target\\karaf.war");

        deployer.deploy(war, "--name=simple", "--contextroot=simple", "--force=true");

        System.out.println("Press Enter to stop server");
        // wait for Enter
        new BufferedReader(new java.io.InputStreamReader(System.in)).readLine();
        try {
            glassFish.stop();
            glassFish.dispose();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
