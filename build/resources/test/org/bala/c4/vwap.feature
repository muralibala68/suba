Feature: Volume weighted average price
    Scenario: Simple test
      Given that there is a list of Trades
        | productId | size | price | timestamp |
        | prod01       | 101  | 12.31 | 123456    |
        | prod01       | 102  | 12.32 | 123457    |
        | prod01       | 103  | 12.33 | 123458    |
      When vwap is calculated
      Then calculated vwap should be 12.34
