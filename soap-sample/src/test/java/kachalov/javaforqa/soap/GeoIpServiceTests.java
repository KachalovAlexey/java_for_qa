package kachalov.javaforqa.soap;


import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
      BBHLocation userLocation = new GeoIP().getGeoIPSoap12().getUserLocation("176.195.84.115");
      assertEquals(userLocation.getCountryCode(), "RUS");
  }

  @Test
  public void testInvalidIp() {
      BBHLocation userLocation = new GeoIP().getGeoIPSoap12().getUserLocation("176.195.84.115");
      assertEquals(userLocation.getCountryCode(), "US");
  }
}
