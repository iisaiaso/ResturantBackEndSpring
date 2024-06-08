package com.ironman.restaurantmanagement.shared.state.enums;

// Lombok annotations

import lombok.Getter;

@Getter
public enum State {
    ENABLED("A"){
        @Override
        public String toString(){
            return "Habilitado";
        }
    },
    DISABLED("E"){
        @Override
        public String toString(){
            return "Habilitado";
        }
    };
    private final String value;
    private final String name;

    State(String value){
        this.value = value;
        this.name = this.toString();
    }
}
