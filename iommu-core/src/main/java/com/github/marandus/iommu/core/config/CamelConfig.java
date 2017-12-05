/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.marandus.iommu.core.config;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO: Missing class level javadoc
 *
 * @author Thomas Rix (thomasrix@exodus-project.net)
 */
@Configuration
public class CamelConfig {

    @Bean
    public CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext camelContext) {

            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {

            }
        };
    }
}
