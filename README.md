# Instant Runoff Voting API

* [Creating a New Voter](#Creating a New Voter)
* [Logging In](#Logging In)
* [Getting a Voter Based on Their Email](#Getting a Voter Based on Their Email)
* [Creating a New poll](#Creating a New poll)
* [Get Poll by ID](#Get Poll by ID)
* [Register to Vote in a Poll](#Register to Vote in a Poll)
* [Cast a Vote in a Poll](#Cast a Vote in a Poll)

[Dictionary of Terms](#Dictionary of Terms)

## What is Instant Runoff Voting?
There are other election systems besides the two party system used by the United States of America for goverment elections. One of the alternative election systems is called Instant Runoff Voting also known as Ranked Choice Voting. The main idea behind this system is to get the choice that the most voters like. Not I could write a long winded description of instant run-off voting but it wouldn't be nearly as good as some of the other ones out there already. So here watch this short video to get the idea:

[Instant Runoff Voting Example (1.5 minutes)](https://www.youtube.com/watch?v=_5SLQXNpzsk)

See? Wasn't that better? Short to the point and clear.

Huh? You still want to know more? Weirdo. But my kind of weirdo!
* [InstantRunOff.com](http://instantrunoff.com/instant-runoff-home/)
* [FairVote.org's Ranked Choice Voting](http://www.fairvote.org/rcv#rcvbenefits)

## Tips for Using This Guide
1. With the exception of creating a new voter and logging in, all requests must be occupied by a valid authentication token. Logging in will supply your token. These tokens expire after 60 minutes of no access, and you will have to login again to get a new token.
1. All parameters must be supplied. Those marked optional can be empty.
1. If a body is specified it is required for the action.

## Bad Authentication Token Result
Any request that doesn't supply a valid authentication token will result in the following:
> Http Status Code: Unauthorized (401)

```json
{ "result":"Bad token supplied" }
```

## Creating a New Voter (user)
Well to start, you are going to need to register as a voter (i.e. create a login). Email address and password combination will be required for logging into the API.

#### Target URL:
`http:\\irvingmichael.com\irv-api\createNewVoter`

#### URL Parameters:
> * **firstname** - 0-45 characters _optional_
> * **lastname** - 0-100 characters _optional_
> * **email** - Your E-mail address, 0-254 characters
>* **password** - Password for your account

#### Returned:
**Success:**
> Http Status Code: OK (200)

**Failure:**
> Http Status Code: Internal Server Error (500)

##Logging In

### Target url:
`http:\\irvingmichael.com\irv-api\login`

#### URL Parameters:
> * **email** - Your E-mail address, 0-254 characters
>* **password** - Password for your account

#### Returned:
**Success:**
> Http Status Code: OK (200)

```json
{ "authtoken":"zh3Pe2K5qDBCQRNIxL6vzpOPzFGPujEw6SYSlmeYmz7TJwXgNWni2GdxILD6d62p" }
```

**Failure:**
> Http Status Code: Unauthorized (401)

```json
{ "result":"Login failed" }
```

## Getting a Voter Based on Their E-mail

### Target url:
`http:\\irvingmichael.com\irv-api\getVoterByEmail`

#### URL Parameters:
> * **authtoken** - The authtoken you were supplied when logging in.
> * **email** - E-mail address of the voter to retrieve, 0-254 characters

#### Returned:
**Success:**
> Http Status Code: OK (200)

```json
{"voterId":1,"firstName":"John","lastName":"Doe","email":"fake@fake.com"}
```

**Failure:**
> Http Status Code: Not Found (404)

```json
{ "result":"Email not found" }
```

## Creating a New poll

### Target url:
`http:\\irvingmichael.com\irv-api\createNewPoll`

#### URL Parameters:
> * **authtoken** - The authtoken you were supplied when logging in.

#### Body
```json
{
  "title": "test api create poll name",
  "description": "api create poll test description",
  "creator": 11,
  "choices": [
    {
      "name": "test api create poll choice 1 name",
      "description": "test api create poll choice 1 description"
    },
    {
      "name": "test api create poll choice 2 name",
      "description": "test api create poll choice 2 description"
    },
    {
      "name": "test api create poll choice 3 name",
      "description": "test api create poll choice 3 description"
    },
    {
      "name": "test api create poll choice 4 name",
      "description": "test api create poll choice 4 description"
    }
  ]
}
```
##### Poll Items
> **title** - Is the title of the poll.
> **description** - This is a longer description of the poll. _optional_
> **creator** - ID of the voter creating the poll.
> **choices** - Array of choices for the poll.

##### Choice Items
> **name** - Name of the choice. Used when casting a vote.
> **description** - Long description of the choice to give more information about it. _optional_

#### Returned:
**Success:**
> Http Status Code: OK (200)

```json
{
   "pollid":4,
   "title":"test api create poll name",
   "description":"api create poll test description",
   "available":false,
   "choices":[
      {
         "id":80,
         "name":"test api create poll choice 1 name",
         "description":"test api create poll choice 1 description",
         "pollId":4
      },
      {
         "id":81,
         "name":"test api create poll choice 2 name",
         "description":"test api create poll choice 2 description",
         "pollId":4
      },
      {
         "id":82,
         "name":"test api create poll choice 3 name",
         "description":"test api create poll choice 3 description",
         "pollId":4
      },
      {
         "id":83,
         "name":"test api create poll choice 4 name",
         "description":"test api create poll choice 4 description",
         "pollId":4
      }
   ],
   "creator":11,
   "winner":0,
   "pollCode":"ns8tfJB8",
   "status":"INITIAL"
}
```
##### Poll Items
> **pollid** - The generated id for the poll
> **title** - Is the title of the poll.
> **description** - This is a longer description of the poll.
> **available - Boolean as to whether the poll should be accessible to people other than the creator.
> **choices** - Array of choices for the poll.
> **creator** - ID of the voter creating the poll.
> **winner** - Winner of the poll if the poll is complete
> **pollcode** - Code required for a voter to register with an election. This should be shared with voters.
> **status** - Current status of the poll

##### Choice Items
> **name** - Name of the choice. Used when casting a vote.
> **description** - Long description of the choice to give more information about it. _optional_

**Failure:**
> Http Status Code: Bad Request (400)

## Get Poll by ID

### Target url:
`http:\\irvingmichael.com\irv-api\getPollById`

#### URL Parameters:
> * **authtoken** - The authtoken you were supplied when logging in.
> * **pollid** - Integer id of poll to return

#### Returned:
**Success:**
> Http Status Code: OK (200)

```json
{
   "pollid":1,
   "title":"Test Poll",
   "description":"This is a test poll.",
   "available":true,
   "creator":1,
   "winner":-1,
   "pollCode":"abcdefgh",
   "status":"OPEN"
}
```
##### Poll Items
> **pollid** - The generated id for the poll
> **title** - Is the title of the poll.
> **description** - This is a longer description of the poll.
> **available - Boolean as to whether the poll should be accessible to people other than the creator.
> **creator** - ID of the voter creating the poll.
> **winner** - Winner of the poll if the poll is complete
> **pollcode** - Code required for a voter to register with an election. This should be shared with voters.
> **status** - Current status of the poll

**Failure:**
> Http Status Code: Bad Request (400)

```json
{ "result":"Poll not found" }
```

## Register to Vote in a Poll

### Target url:
`http:\\irvingmichael.com\irv-api\registerWithPoll`

#### URL Parameters:
> * **authtoken** - The authtoken you were supplied when logging in.
> * **pollcode** - 8 character code associated with poll
> * **voterid** - Voter id of the voter to register with the poll

#### Returned:
**Success:**
> Http Status Code: OK (200)

```json
{ "result":"success" }
```

**Failure:**
> Http Status Code: Bad Request (400)

```json
{ "result":"Bad poll code supplied" }
```
> Http Status Code: Bad Request (400)

```json
{ "result":"Bad token supplied" }
```

## Cast a Vote in a Poll

### Target url:
`http:\\irvingmichael.com\irv-api\`

#### URL Parameters:
> * **authtoken** - The authtoken you were supplied when logging in.

#### Body
```json
{
  "pollId": 1,
  "voterId": 11,
  "voteRankings": {
    "1": 1,
    "2": 2,
    "3": 3,
    "4": 4
  }
}
```
##### Poll Items
> **pollid** - The generated id for the poll
> **voterid** - ID of current voter.
> **voteRankings** - Map of choice IDs and associated rank.

##### Voter Rankings Items
> Key: Choice ID of the choice being ranked
> Value: Ranked to be associated with choice

#### Returned:
**Success:**
> Http Status Code: OK (200)

```json
{ "result":"success" }
```

**Failure:**
> Http Status Code: Internal Server Error (500)

```json
{ "result":"There was an error casting your vote." }
```

## Dictionary of Terms

| Term | Definition |
|------|------------|
| **Authentication Token** | An API generated string that is required as part of most requests to access the API. Supplied upon a valid login. |
| **Choice** | A single options offered in a poll. |
| **Creator** | The voter that created a specific poll. |
| **Poll** | The encompassing term for a topic to vote on and all information associated with it. |
| **Poll Code** | An 8 character code that is specific to a single poll. Used to register a voter with a poll. This should be shared with voters. |
| **Status** | Current status of the poll:<br/><ul><li>INITIAL - Poll created but not open to registering or voting</li><li>OPEN - Poll is open for registering and voting</li><li>CLOSED - Poll is closed by the creator. Registering and voting unavailable.</li><li>COMPLETE - Poll is complete and winner has been calculated.</li></ul> |
| **Voter** | This is a user of the api. All user are also voters |
