package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import com.yandex.disk.rest.Credentials;
import com.yandex.disk.rest.DownloadListener;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class LoadImageCommand extends Command {

    public LoadImageCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            Credentials credentials = new Credentials("Elizabeth Kroffel", "AgAAAAA2bG-8AAZf4WgfBxTd3kG_m1dfU8xSNd0");
            RestClient restClient = new RestClient(credentials);
            restClient.downloadFile(getRequest().getAttribute("imageUrl").toString(), new DownloadListener() {
                @Override
                public OutputStream getOutputStream(boolean b) throws IOException {
                    return getResponse().getOutputStream();
                }
            });
        } catch (IOException | ServerException  ignored){ }
    }

    @Override
    public void executePost() throws ControllerException {

    }

}
