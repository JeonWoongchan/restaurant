package com.example.restaurant.tool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationUtils {

  public static String filterPhoneNumber(String phoneNumber) {
    // 정규 표현식 패턴 설정 (숫자 이외의 문자 모두)
    Pattern pattern = Pattern.compile("[^0-9]");

    // 정규 표현식 패턴과 매치되는 부분 찾기
    Matcher matcher = pattern.matcher(phoneNumber);

    // 매치된 부분을 제거하여 숫자만 남기기
    String filteredPhoneNumber = matcher.replaceAll("");

    return filteredPhoneNumber;
  }

  // 날짜 형식 메소드
  public static synchronized String formatDateTime(LocalDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public static synchronized String formatDate(LocalDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  // END date 정하는 메소드
  public static synchronized String calculateEndDateTime(String reserveDate) {
    LocalDateTime LdateTime = LocalDateTime.parse(reserveDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    return formatDateTime(LdateTime.plusHours(8)); // 8시간을 더한 새로운 날짜와 시간
  }

  // 날짜 month 에서 0누락되는거 추가하는 메소드
  public static synchronized String addLeadingZeroIfNeeded(String date) {
    // 날짜와 시간을 구분합니다.
    String[] parts = date.split(" ");
    String[] dateParts = parts[0].split("-");

    // 날짜 부분에서 월과 일에 0을 추가합니다.
    StringBuilder modifiedDate = new StringBuilder();
    for (int i = 0; i < dateParts.length; i++) {
      if (dateParts[i].length() == 1) {
        modifiedDate.append("0").append(dateParts[i]);
      } else {
        modifiedDate.append(dateParts[i]);
      }
      if (i < dateParts.length - 1) {
        modifiedDate.append("-");
      }
    }
    // 시간 부분을 다시 추가합니다.
    if (parts.length > 1) {
      modifiedDate.append(" ").append(parts[1]);
    }

    return modifiedDate.toString();
  }

  public static synchronized long generateRandomLong(long min, long max) {
    Random random = new Random();
    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }
    // 범위 내의 랜덤 숫자 생성
    long range = max - min;
    long fraction = (long) (range * random.nextDouble());
    return fraction + min;
  }

  public static synchronized LocalDate convertToDatetime(String dateString) {
    // 날짜 형식 지정
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 문자열을 LocalDate 객체로 변환
    LocalDate date = LocalDate.parse(dateString, formatter);
    return date;
  }
  public static synchronized String extractTimeFromString(String dateTimeStr) {
    // 문자열에서 LocalDateTime 객체 생성
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    // LocalDateTime에서 시간(Hour) 부분만 추출하여 문자열로 변환
    String hourStr = dateTime.format(DateTimeFormatter.ofPattern("HH"));
    return hourStr;
  }
}
