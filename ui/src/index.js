import 'core-js/features/set';
import 'core-js/features/map';
import React from 'react';
import ReactDOM from 'react-dom';
import ReactDOMServer from 'react-dom/server';
import './index.css';
import App from './App';

console.log("evaluated...");

global.foobar = function(report) {
    console.log(report.projectName);
    console.log(report.campaignName);
    console.log(report.foobar.x);
    console.log(report.foobar.y);
    console.log(report.foobar.name);
    return ReactDOMServer.renderToString(React.createElement(App, {}));
}
