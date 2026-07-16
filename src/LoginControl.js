import React, { Component } from 'react';

// Functional components for buttons and greetings
function LoginButton(props) {
    return <button onClick={props.onClick}>Login</button>;
}

function LogoutButton(props) {
    return <button onClick={props.onClick}>Logout</button>;
}

function UserGreeting() {
    return <h1>Welcome back</h1>;
}

function GuestGreeting() {
    return <h1>Please sign up.</h1>;
}

// Main LoginControl component
class LoginControl extends Component {
    constructor(props) {
        super(props);
        this.handleLoginClick = this.handleLoginClick.bind(this);
        this.handleLogoutClick = this.handleLogoutClick.bind(this);
        this.state = { isLoggedIn: false };
    }

    handleLoginClick() {
        this.setState({ isLoggedIn: true });
    }

    handleLogoutClick() {
        this.setState({ isLoggedIn: false });
    }

    render() {
        const isLoggedIn = this.state.isLoggedIn;
        let button;

        // Element variable for conditional rendering
        if (isLoggedIn) {
            button = <LogoutButton onClick={this.handleLogoutClick} />;
        } else {
            button = <LoginButton onClick={this.handleLoginClick} />;
        }

        return (
            <div>
                {isLoggedIn ? <UserGreeting /> : <GuestGreeting />}
                {button}
            </div>
        );
    }
}

export default LoginControl;