# qa_guru4_16_rest_assured

Home task #16 for QA.GURU 4

There are two REST API tests for demo internet shop http://demowebshop.tricentis.com/
You have to register own user for running this tests.

- positive test searchs "Laptop" on this shop and expects text "title=\"Show details for 14.1-inch Laptop\"" as response
- negative test searchs "Blabla" on this shop and expects text "No products were found that matched your criteria." as response

All search criterions are in src/test/java/utils/SearchDataConfig.java.
You can use command line for config own search criterions.

*Usage*
qa_guru4_16_rest_assured$ gradle test --tests "tests.SearchTests" -Dlogin=YouOwn@Email -Dpassword=YouOwnPassword -DnegativeSearch=Blabla -DpositiveSearch=Laptop
