# Application Architecture
![Architecture](https://github.com/Rahul-4525/MicroservicesArch/blob/master/Architecture.png?raw=true)

## Steps to invoke APIs
### For creating a user invoke this
`curl --location 'http://localhost:9001/api/auth/addUser' \
    --header 'Content-Type: application/json' \
--data-raw '{
    "userName":"johnapplesedu@gmail.com",
    "password":"john123@"
}'`
### For getting a token invoke this
`curl --location 'http://localhost:9001/api/auth/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userName":"johnapplesedu@gmail.com",
    "password":"john123@"
}'`
### For validating a token invoke this
`curl --location 'http://localhost:9001/api/auth/validateToken/PASTE_TOKEN_HERE'`

### create a merchant account with this below payload
`curl --location 'http://localhost:9000/api/account/add' \
--header 'Content-Type: application/json' \
--data '{
    "accountNumber":"35625",
    "balance":"6"
}'`

### For adding a billing account for customer/merchant
`curl --location 'http://localhost:9000/api/account/add' \
--header 'Content-Type: application/json' \
--data '{
    "accountNumber":"35624",
    "balance":"6"
}'`

### For initiating a payment
`curl --location --request GET 'http://localhost:9004/api/payments/initiate/PASTE_TOKEN_HERE' \
    --header 'Content-Type: application/json' \
    --data '{
        "billNumber":"1",
        "billAmount":"1.0",
        "accountNumber":"35624"
    }'`