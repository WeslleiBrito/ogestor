package com.example.ogestor.util;

public class LimpaNomeVendedor {

    public static String nomeFormatado(String nome) {
        StringBuilder primeiroNome = new StringBuilder();
        StringBuilder conjugador = new StringBuilder();
        StringBuilder sobrenome = new StringBuilder();
        int espaco = 0;

        for(int i = 0; i < nome.length(); i++ ) {

            if (nome.charAt(i) != ' ') {
                if(espaco == 0){
                    primeiroNome.append(nome.charAt(i));
                }else if(espaco == 1 && conjugador.isEmpty()){
                    sobrenome.append(nome.charAt(i));
                }else if(espaco == 2 && !conjugador.isEmpty()) {
                    sobrenome.append(nome.charAt(i));
                }
            }else{
                if(nome.charAt(i + 3) == ' ' && espaco == 0){
                    conjugador.append(nome.charAt(i + 1));
                    conjugador.append(nome.charAt(i + 2));
                }

                if(nome.charAt(i + 4) == ' ' && espaco == 0){
                    conjugador.append(nome.charAt(i + 1));
                    conjugador.append(nome.charAt(i + 2));
                    conjugador.append(nome.charAt(i + 3));
                }

                espaco += 1;

            }

        }

        return primeiroNome + " " + conjugador + " " + sobrenome;
    }
}
