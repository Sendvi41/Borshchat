package com.sendvi41.borshdesk.utils;

import com.sendvi41.borshdesk.controllers.MenuController;
import com.sendvi41.borshdesk.controllers.QueueController;
import com.sendvi41.borshdesk.dto.Template;
import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.Label;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class Tools {
    private static final Logger logger = Logger.getLogger(Tools.class.getName());
    static List<LabelChat> receivedChats = new LinkedList<>();
    static List<LabelChat> listchats = new LinkedList<>();
    static List<Template> templates = new LinkedList<>();

    static public synchronized List<Template>getListTemplates(){
        return templates;
    }

    static public synchronized void setListTemplates(List<Template> listtemplates){
        templates = listtemplates;
    }




    static public synchronized void addNewChat(String id, String firstmessage){
                List<LabelChat> result = listchats.stream()
                .filter(item -> item.getId().equals(Long.parseLong(id)))
                .collect(Collectors.toList());
        if(!result.isEmpty())
        {
            logger.info("Chat already exist");
            result.get(0).setMessage("client", firstmessage);
        }else {
            LabelChat newLabel = new LabelChat(Long.parseLong(id), new Label(id));
            newLabel.setMessage("client", firstmessage);
            listchats.add(newLabel);
        }

    }
    static public synchronized List<LabelChat> getListChats(){
        return listchats ;
    }

    static public synchronized List<LabelChat> getReceivedChats(){
        return receivedChats;
    }

    static public synchronized void deleteChatFromMainQueue(String id){

        LabelChat a = listchats.stream()
                .filter(item -> item.getId().equals(Long.parseLong(id)))
                .collect(Collectors.toList()).get(0);
            a.getLabel().setStyle("");
        receivedChats.add(a);

        listchats = listchats.stream()
                .filter(item -> !item.getId().equals(Long.parseLong(id)))
                .collect(Collectors.toList());
        logger.info("Chat have taken");
    }


    static public synchronized void addPersonalChat(String id, String message, String author){
        List<LabelChat> result = receivedChats.stream()
                .filter(item -> item.getId().equals(Long.parseLong(id)))
                .collect(Collectors.toList());
        if(!result.isEmpty())
        {
            logger.info("Chat already exist");
            result.get(0).setMessage(author, message);
        }else {
            LabelChat newLabel = new LabelChat(Long.parseLong(id), new Label(id));
            newLabel.setMessage(author, message);
            listchats.add(newLabel);
        }

    }

}


