package com.sendvi41.borshdesk.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class TemplateController extends FxController  {
    private final String source = "fxml/templateScene.fxml";
}
