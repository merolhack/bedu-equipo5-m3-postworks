package org.bedu.java.backend.pw;

import java.util.HashSet;
import java.util.Set;

import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.core.importer.Locations;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.LocationProvider;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class InterfaceRulesTest {

    @ArchTest
    static final ArchRule interfaces_should_not_have_names_ending_with_the_word_interface =
            noClasses().that().areInterfaces().should().haveNameMatching(".*Interface");

    @ArchTest
    static final ArchRule interfaces_should_not_have_simple_class_names_containing_the_word_interface =
            noClasses().that().areInterfaces().should().haveSimpleNameContaining("Interface");

    @ArchTest
    static final ArchRule interfaces_must_not_be_placed_in_implementation_packages =
            noClasses().that().resideInAPackage("..impl..").should().beInterfaces();

}
