Feature: Add a place in google maps
@addplace
  Scenario : Verify add place is added using addplace ApiGiven Add page payload
  When user calls "add place" api using post request
  Then Api is call is success and status code should be 201
  And "status" is "ok"  Given Add place payload
