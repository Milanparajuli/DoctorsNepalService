package com.milan.doctorsNepal.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;

@Component
public final class InitialPatch {
    private static final String PATCHFOLDER = "/static";

    private InitialPatch() {

    }

    public static void inital(String baseServerFolder, DataSource dataSource) {
        ClassPathResource dataResource3 = new ClassPathResource(
                baseServerFolder + File.separator + "data.sql");
        ResourceDatabasePopulator populator1 = new ResourceDatabasePopulator(dataResource3);
        populator1.execute(dataSource);
    }
}
