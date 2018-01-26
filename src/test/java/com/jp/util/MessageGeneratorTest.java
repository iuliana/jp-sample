package com.jp.util;

import com.jp.base.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by iuliana.grajdeanu on 1/26/18.
 */
public class MessageGeneratorTest {

    MessageGenerator mg;

    @BeforeEach
    public void setUp() {
        mg = new MessageGenerator();
    }

    @Test
    public void testGenerator() {
        Message message = mg.getMessage();
        assertNotNull(message);
        assertNotNull(message.getMessageType());
        assertTrue(message.getSales().size() >= 1);
    }
}
