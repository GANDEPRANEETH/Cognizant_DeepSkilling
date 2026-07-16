import React, { Component } from 'react';

class EventExamples extends Component {
    constructor(props) {
        super(props);
        this.state = { counter: 5, amount: '', currency: '' };
    }

    // Counter logic
    increment = () => {
        this.setState({ counter: this.state.counter + 1 });
        alert("Hello! Member1");
    };

    decrement = () => {
        this.setState({ counter: this.state.counter - 1 });
    };

    // Alert methods
    sayWelcome = (msg) => alert(msg);
    onPress = () => alert("I was clicked");

    // Currency Converter logic
    handleSubmit = (e) => {
        e.preventDefault();
        const result = this.state.amount * 80; // Assuming 1 Euro = 80 Rupees
        alert(`Converting to Euro Amount is ${result}`);
    };

    render() {
        return (
            <div>
                <h1>{this.state.counter}</h1>
                <button onClick={this.increment}>Increment</button>
                <button onClick={this.decrement}>Decrement</button>
                <button onClick={() => this.sayWelcome("welcome")}>Say welcome</button>
                <button onClick={this.onPress}>Click on me</button>

                <h1 style={{ color: 'green' }}>Currency Convertor!!!</h1>
                <form onSubmit={this.handleSubmit}>
                    Amount: <input type="text" onChange={(e) => this.setState({ amount: e.target.value })} /><br />
                    Currency: <input type="text" onChange={(e) => this.setState({ currency: e.target.value })} /><br />
                    <button type="submit">Submit</button>
                </form>
            </div>
        );
    }
}
export default EventExamples;