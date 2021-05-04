package com.sendvi41.borshdesk.utils;

import com.sendvi41.borshdesk.controllers.MenuController;
import com.sendvi41.borshdesk.controllers.QueueController;
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
    static List<LabelChat> listchats = new LinkedList<>();

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
        return listchats;
    }
}


