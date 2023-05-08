package com.demoprojekt.webshop.schedulerjobs

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SendAnalyticsJob {


    @Scheduled(cron = "0 0 2 * * ?") //alle 5 Sekunden
    fun sendAnalytics() {
        println("Analytics are being sent")
    }

    @Scheduled(cron = "0 0 2 1 * ?") //alle 5 Sekunden
    fun nextJob() {
        println("Next Scheduler Job!")
    }
}