package ru.inwords.bgps.service;

import net.progruzovik.bus.message.RestReceiver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MessageChecker {

    private volatile Instant lastCheckTime = null;
    private final RestReceiver restReceiver;

    public MessageChecker(RestReceiver restReceiver) {
        this.restReceiver = restReceiver;
    }

    @Scheduled(fixedDelay = 3000)
    public synchronized void checkMessages() {
        lastCheckTime = restReceiver.receiveMessages(lastCheckTime);
    }
}