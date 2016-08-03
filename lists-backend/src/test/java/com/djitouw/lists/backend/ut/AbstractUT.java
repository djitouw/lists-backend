package com.djitouw.lists.backend.ut;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract class for unitary tests.
 * 
 * @author Djitouw
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-context.xml"})
public abstract class AbstractUT {

}
