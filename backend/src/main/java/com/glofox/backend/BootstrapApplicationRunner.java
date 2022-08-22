package com.glofox.backend;

import com.glofox.backend.models.Member;
import com.glofox.backend.models.Owner;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;
import com.glofox.backend.repositories.StudioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
public class BootstrapApplicationRunner implements CommandLineRunner {

    StudioRepository repository;

    public BootstrapApplicationRunner(StudioRepository studioRepository) {
        this.repository = studioRepository;
    }

    //Runs on application start to populate the persistence with some initial data
    @Override
    public void run(String...args) throws Exception {
        //Studio
        Studio studio54 = new Studio("Studio 54", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        //Owner
        Owner andy = new Owner("andy");
        studio54.setOwner(andy);

        //Classes
        StudioClass spinning = new StudioClass(
            "spinning",
            new SimpleDateFormat("dd-MM-yyyy").parse("22-09-2022"),
            new SimpleDateFormat("dd-MM-yyyy").parse("31-09-2022"),
            123
        );

        StudioClass pilates = new StudioClass(
            "pilates",
            new SimpleDateFormat("dd-MM-yyyy").parse("23-09-2022"),
            new SimpleDateFormat("dd-MM-yyyy").parse("31-09-2022"),
            123
        );
        studio54.getClasses().add(spinning);
        studio54.getClasses().add(pilates);

        //Members
        Member steve = new Member("steve");
        Member jane = new Member("jane");

        studio54.getMembers().add(steve);
        studio54.getMembers().add(jane);

        this.repository.createStudio(studio54);

    }
}