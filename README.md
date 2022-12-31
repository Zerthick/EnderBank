# EnderBank
A simple paper plugin to turn ender chests into banks with a fee.

## Plugin configuration
A sample plugin configuration is given below
```yaml
# The amount of currency a player will be charged for using an EnderBank
fee: 100
# The message shown to a player when successfully opening an Enderbank
successMessage: "Thank you for using EnderBank!"
# The message shown to a player when they are unable to open an Enderbank due to lack of funds
failureMessage: "We're sorry but you do not have the required funds to use EnderBank!"
# The message shown to a player when they don't have permission to open an Enderbank
permissionsMessage: "We're sorry but you do not have the required permissions to use EnderBank!"
```

## Plugin permissions
* `enderbank.use` - Gives players permission to use an EnderBank
* `enderbank.free` - Gives players permission to bypass the fee for using an EnderBank
