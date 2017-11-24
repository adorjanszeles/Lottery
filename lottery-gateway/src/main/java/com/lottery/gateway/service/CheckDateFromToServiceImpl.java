package com.lottery.gateway.service;

import com.lottery.gateway.exceptions.InvalidDateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * {@link CheckDateFromToService} interfész implementációja
 */
@Service
@PropertySource("classpath:lottery-web.properties")
public class CheckDateFromToServiceImpl implements CheckDateFromToService {

    private Environment env;
    private MinDateService minDateService;

    @Autowired
    public CheckDateFromToServiceImpl(Environment env, MinDateService minDateService) {
        this.env = env;
        this.minDateService = minDateService;
    }

    @Override
    public boolean checkFromTo(String from, String to, String access_token)
            throws ParseException, InvalidDateException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = format.parse(from);
        Date toDate = format.parse(to);
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        String url = this.env.getProperty("lottery-web.get-min-date");
        String min = this.minDateService.getMinDate(url, access_token);
        Date minDate = format.parse(min);
        if (toDate.before(fromDate) || fromDate.before(minDate) || toDate.before(minDate) || today.before(fromDate) ||
            today.before(toDate)) {
            throw new InvalidDateException("Wrong date interval or from date is after to date. Lottery date intervals available from: " + minDate.toString()
            );
        }
        return true;
    }

}
