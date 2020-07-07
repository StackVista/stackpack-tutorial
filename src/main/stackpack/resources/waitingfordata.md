## The Tutorial StackPack is waiting for your action, please send some topology to StackState

The StackPack has been installed.

Now, you can push data to this StackPack to check if it works.

## Push data using an agent check

Check out the [push-integration tutorial](https://docs.stackstate.com/develop/tutorials/push_integration_tutorial) for instructions on how to push data to this StackPack.

Beware that the instance type and URL are different than the one used in the tutorial:

```
Instance type (source identifier): tutorial
Instance URL: tutorial://tutorial-1
```

The `example.py` check in the [push-integration tutorial repository](https://github.com/StackVista/push-integration-tutorial) must be changed with the above parameters.

## Push data using `curl`

1. Save a file named `topology.json` with the following content

```json
{
   "apiKey":"{{config.apiKey}}",
   "collection_timestamp":1467037580.595086,
   "internalHostname":"lnx-343242.srv.stackstate.com",
   "topologies":[
      {
         "start_snapshot": false,
         "stop_snapshot": false,
         "instance":{
            "type":"tutorial",
            "url":"tutorial://tutorial-1"
         },
         "components":[
            {
               "externalId":"myDummyApp",
               "type":{
                  "name":"application"
               },
               "data":{
                  "ip_addresses":["172.17.0.8"],
                  "labels":["label1", "category:label2"]
               }
            }
         ],
         "relations":[]
      }
   ]
}
```

2. Run this curl command to push the data to StackState:

``` bash
curl -v user:password -X POST -H "Content-Type: application/json" --data-ascii @topology.json "{{config.baseUrl}}/stsAgent/intake/?api_key={{config.apiKey}}"
```

The [StackPack tutorial](https://docs.stackstate.com/develop/tutorials) on the StackState documentation site explains how to use it.
