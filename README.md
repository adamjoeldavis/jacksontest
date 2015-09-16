# jacksontest
Test case for Jackson JsonCreator issue

The main method is found in the DataList class.  This code functions correctly in Jackson 2.4.5, but fails in 2.5 and up.

The test creates a DataList with a few entries in it, serializes the list, then attempts to deserialize it into a new DataList instance.  Failure happens at the deserialization step
