package org.bedu.java.backend.pw.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "org.bedu.java.backend.pw",
        importOptions = { ImportOption.DoNotIncludeTests.class })
public class ArchitectureTest {
    private static final String DOMAIN_LAYER = "Domain";
    static final String DOMAIN_LAYER_PACKAGES = "org.bedu.java.backend.pw.domain..";
    private static final String APPLICATION_LAYER = "Application";
    static final String APPLICATION_LAYER_PACKAGES = "org.bedu.java.backend.pw.application..";
    static final String ADAPTERS_LAYER_PACKAGES = "org.bedu.java.backend.pw.adapters..";
    private static final String ADAPTERS_LAYER = "Adapters";
    static final String PRIMARY_ADAPTERS_PACKAGES = "org.bedu.java.backend.pw.primary..";
    static final String SECONDARY_ADAPTERS_PACKAGES = "org.bedu.java.backend.pw.secondary..";

    static JavaClasses classes;

    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .consideringAllDependencies()
            .layer(DOMAIN_LAYER).definedBy(DOMAIN_LAYER_PACKAGES)
            .layer(APPLICATION_LAYER).definedBy(APPLICATION_LAYER_PACKAGES)
            .layer(ADAPTERS_LAYER).definedBy(ADAPTERS_LAYER_PACKAGES)
            .whereLayer(ADAPTERS_LAYER).mayNotBeAccessedByAnyLayer()
            .whereLayer(APPLICATION_LAYER).mayOnlyBeAccessedByLayers(PRIMARY_ADAPTERS_PACKAGES)
            .whereLayer(DOMAIN_LAYER).mayOnlyBeAccessedByLayers(PRIMARY_ADAPTERS_PACKAGES, APPLICATION_LAYER_PACKAGES);

}
