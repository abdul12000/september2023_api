Feature: SocialNetworking
  As a User
  I want to be able to use the service - JSONPlaceholder - Free Fake REST API
  so that I can  make posts

#  @TestToRun
  Scenario Outline:Get a specific post
    Given service is up and running
    When I send GET request to get a specific post using id "<id>"
    Then the specific post details"<id>", "<title>" and "<body>" are returned with status code of 200
    Examples:
      | id | title                                                                      | body                                                                                                                                                              |
      | 1  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |


#  @TestToRun
  Scenario Outline:Create a new post
    Given service is up and running
    When I post with these details "<userId>", "<title>" and "<body>"
    Then I should get response with statusCode of 201 and the following "<userId>", "<title>" and "<body>"
    Examples:
      | userId | title                     | body                                                        |
      | 200    | My last holiday to Brazil | I went to brazil with my family and we had a very good time |


#  @TestToRun
  Scenario Outline: Delete a specific post
    Given service is up and running
    When I send Delete request to delete a specific post using id "<id>"
    Then status code of 200 is returned
    Examples:
      | id | title                                                                      | body                                                                                                                                                              |
      | 1  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |


  @TestToRun
  Scenario Outline:Update a record with PUT
    Given service is up and running
    When I update record "<id>" with these details "<userId>", "<title>" and "<body>"
    Then I should get response with statusCode of 200 and the following "<userId>", "<title>" and "<body>" for the Update
    Examples:
      | id | userId | title                     | body                                                        |
      | 1  | 200    | My last holiday to Brazil | I went to brazil with my family and we had a very good time |