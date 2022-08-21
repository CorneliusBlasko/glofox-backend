package com.glofox.backend;

import com.glofox.backend.models.Owner;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;
import com.glofox.backend.repositories.OwnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
public class BootstrapApplicationRunner implements CommandLineRunner {

    OwnerRepository repository;

    public BootstrapApplicationRunner(OwnerRepository ownerRepository) {
        this.repository = ownerRepository;
    }

    @Override
    public void run(String...args) throws Exception {
        //Studio
        Studio studio54 = new Studio("Studio 54", new ArrayList<>(), new ArrayList<>());

        //Owner
        Owner andy = new Owner("andy", studio54);

        //Class
        StudioClass spinningClass = new StudioClass(
            "spinning",
            new SimpleDateFormat("dd-MM-yyyy").parse("22-09-2022"),
            new SimpleDateFormat("dd-MM-yyyy").parse("23-09-2022"),
            123
        );

        studio54.getClasses().add(spinningClass);

        this.repository.createOwner(andy);

    }
}