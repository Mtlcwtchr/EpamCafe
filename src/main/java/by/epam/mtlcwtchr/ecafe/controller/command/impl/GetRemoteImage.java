package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.DependenciesLoader;
import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.servlet.CommonServlet;
import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.DownloadListener;
import com.yandex.disk.rest.RestClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetRemoteImage extends Command {

    public GetRemoteImage(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() {
        try {
            getImageWhileNoInternetConnectionEstablished();
        } catch (IOException e) {
            StaticDataHandler.INSTANCE.getLOGGER().error(e);
        }
    }

    @Override
    public void executePost() {

    }

    public void getImageWhileNoInternetConnectionEstablished() throws IOException {
        final String dirPath = "D:\\Java\\EPAM\\EcafeWebapp\\imgs\\";
        System.out.println(getRequest().getParameter("url").substring(getRequest().getParameter("url").lastIndexOf('/') + 1));
        getResponse().getOutputStream().write(Files.readAllBytes(Paths.get(dirPath + getRequest().getParameter("url").substring(getRequest().getParameter("url").lastIndexOf('/')))));
        getResponse().getOutputStream().flush();
    }

    public void getImageWhileInternetConnectionEstablished() {
        try {
            Credentials credentials = new Credentials("Elizabeth Kroffel", "AgAAAAA2bG-8AAZf4WgfBxTd3kG_m1dfU8xSNd0");
            RestClient restClient = new RestClient(credentials);
            restClient.downloadFile(getRequest().getParameter("url"), new DownloadListener() {
                @Override
                public OutputStream getOutputStream(boolean b) {
                    try {
                        return getResponse().getOutputStream();
                    } catch (IOException ex) {
                        StaticDataHandler.INSTANCE.getLOGGER().error(ex);
                        return null;
                    }
                }
            });
        } catch (Exception  ex){
            StaticDataHandler.INSTANCE.getLOGGER().error(ex);
        }
    }

}
