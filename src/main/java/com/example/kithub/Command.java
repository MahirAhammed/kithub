package com.example.kithub;

import org.springframework.http.ResponseEntity;

public interface Command <Entity, Response>{

    public ResponseEntity<Response> execute(Entity e);
}
