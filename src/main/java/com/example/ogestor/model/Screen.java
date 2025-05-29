package com.example.ogestor.model;

public enum Screen {
    LOGIN("/com/example/ogestor/windows/login/login.fxml", "Login"),
    SIGNUP("/com/example/ogestor/windows/signup/signup.fxml", "Cadastro");

    private final String fxmlPath;
    private final String title;

    Screen(String fxmlPath, String title) {
        this.fxmlPath = fxmlPath;
        this.title = title;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public String getTitle() {
        return title;
    }
}
