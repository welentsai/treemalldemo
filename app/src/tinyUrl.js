import React, {Component} from 'react';
import {Button, Form, FormGroup, Label, Input, ListGroup, ListGroupItem} from 'reactstrap';

class TinyUrl extends Component {
    state = {
        isLoading: true,
        showUrl: false
    };

    constructor(props) {
        super(props);
        this.onSubmit = this.onSubmit.bind(this);
    }

    async onSubmit(e) {
        e.preventDefault();
        const data = new FormData(e.target);
        try {
            const response = await fetch('/tinyUrl', {
                method: 'POST',
                body: data,
            });
            const text = await response.text();
            if(response.status === 200){  // Success
                this.setState({respUrl: text});
                this.setState({showUrl: true});
            }
        } catch(e) {
            console.log("Oops ...")
        }
    }

    render() {
        const {showUrl, respUrl} = this.state;

        return (
            <div>
                <ListGroup>
                    <ListGroupItem>
                        <Form onSubmit={this.onSubmit}>
                            <FormGroup>
                                <Label for="url">URL Shortner</Label>
                                <Input type="text" name="url" id="url" placeholder="with a placeholder"/>
                            </FormGroup>
                            <Button outline color="success" id="submit">Submit</Button>
                        </Form>
                    </ListGroupItem>

                    {showUrl && <ListGroupItem className="respUrl"><a href={respUrl}>{respUrl}</a></ListGroupItem>}
                </ListGroup>
            </div>
        );
    }
}

export default TinyUrl;