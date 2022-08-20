package com.glofox.backend;

import com.glofox.backend.models.StudioClass;
import com.glofox.backend.repositories.ClassRepository;
import com.glofox.backend.repositories.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BootstrapApplicationRunner implements CommandLineRunner {

    Repository repository;

    public BootstrapApplicationRunner(ClassRepository classRepository) {
        this.repository = classRepository;
    }

    @Override
    public void run(String...args) throws Exception {
        StudioClass spinningClass = new StudioClass(
            "spinning",
            new SimpleDateFormat("dd/MM/yyyy").parse("22-09-2022"),
            new SimpleDateFormat("dd/MM/yyyy").parse("23-09-2022"),
            123
        );

        this.repository.create(spinningClass);
    }
}