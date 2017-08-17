var App = React.createClass({

    loadEmployeesFromServer: function () {
        var self = this;
        $.ajax({
            url: "http://localhost:8080/api/flats"
        }).then(function (data) {
            self.setState({employees: data._embedded.flats});
        });
    },

    getInitialState: function () {
        return {employees: []};
    },

    componentDidMount: function () {
        this.loadEmployeesFromServer();
    },

    render() {
        return ( <EmployeeTable employees={this.state.employees}/> );
    }
});

var Employee = React.createClass({
    render: function() {
        return (<div>employee</div>);
    }
});

var EmployeeTable = React.createClass({
    render: function() {
        var rows = [];
        this.props.employees.forEach(function(employee) {
            rows.push(<Employee employee={employee} />);
        });
        return (
                <div className="row">
                    <div className="col-xs-12">

                        <div className="box">
                            <div className="box-header">
                            </div>
                            <div className="box-body">
                                <table className="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>Link</th>
                                        <th>Miasto</th>
                                        <th>Dzielnica</th>
                                        <th>Liczba Pokoi</th>
                                        <th>od kogo</th>
                                        <th>Cena</th>
                                        <th>Czynsz</th>
                                        <th>Kaucja</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        {rows}
                                    </tbody>
                        </table>
                    </div>
                </div>

            </div>

            <div class="col-sm-5"><div class="dataTables_info" id="example2_info" role="status" aria-live="polite">Showing 1 to 1 of 1 entries</div></div><div class="col-sm-7"><div class="dataTables_paginate paging_simple_numbers" id="example2_paginate"><ul class="pagination"><li class="paginate_button previous disabled" id="example2_previous"><a href="#" aria-controls="example2" data-dt-idx="0" tabindex="0">Previous</a></li><li class="paginate_button active"><a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0">1</a></li><li class="paginate_button next disabled" id="example2_next"><a href="#" aria-controls="example2" data-dt-idx="2" tabindex="0">Next</a></li></ul></div></div>    </div>);
    }
});


var Employee = React.createClass({
    render: function() {
        return (
            <tr>
                <td><a href={this.props.employee._links.self.href}>Link</a></td>
                <td>{this.props.employee.place}</td>
                <td>{this.props.employee.district}</td>
                <td>{this.props.employee.num_rooms}</td>
                <td>{this.props.employee.type_advertiser}</td>
                <td>{this.props.employee.price}</td>
                <td>{this.props.employee.extra_rent}</td>
                <td>{this.props.employee.bail}</td>
            </tr>);
    }
});

ReactDOM.render(<App />, document.getElementById('root') );

'use strict';

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

/* Pagination Component
-------------------------------------------------*/

var propTypes = {
    items: React.PropTypes.array.isRequired,
    onChangePage: React.PropTypes.func.isRequired,
    initialPage: React.PropTypes.number
};

var defaultProps = {
    initialPage: 1
};

var Pagination = function (_React$Component) {
    _inherits(Pagination, _React$Component);

    function Pagination(props) {
        _classCallCheck(this, Pagination);

        var _this = _possibleConstructorReturn(this, _React$Component.call(this, props));

        _this.state = { pager: {} };
        return _this;
    }

    Pagination.prototype.componentWillMount = function componentWillMount() {
        // set page if items array isn't empty
        if (this.props.items && this.props.items.length) {
            this.setPage(this.props.initialPage);
        }
    };

    Pagination.prototype.componentDidUpdate = function componentDidUpdate(prevProps, prevState) {
        // reset page if items array has changed
        if (this.props.items !== prevProps.items) {
            this.setPage(this.props.initialPage);
        }
    };

    Pagination.prototype.setPage = function setPage(page) {
        var items = this.props.items;
        var pager = this.state.pager;

        if (page < 1 || page > pager.totalPages) {
            return;
        }

        // get new pager object for specified page
        pager = this.getPager(items.length, page);

        // get new page of items from items array
        var pageOfItems = items.slice(pager.startIndex, pager.endIndex + 1);

        // update state
        this.setState({ pager: pager });

        // call change page function in parent component
        this.props.onChangePage(pageOfItems);
    };

    Pagination.prototype.getPager = function getPager(totalItems, currentPage, pageSize) {
        // default to first page
        currentPage = currentPage || 1;

        // default page size is 10
        pageSize = pageSize || 10;

        // calculate total pages
        var totalPages = Math.ceil(totalItems / pageSize);

        var startPage, endPage;
        if (totalPages <= 10) {
            // less than 10 total pages so show all
            startPage = 1;
            endPage = totalPages;
        } else {
            // more than 10 total pages so calculate start and end pages
            if (currentPage <= 6) {
                startPage = 1;
                endPage = 10;
            } else if (currentPage + 4 >= totalPages) {
                startPage = totalPages - 9;
                endPage = totalPages;
            } else {
                startPage = currentPage - 5;
                endPage = currentPage + 4;
            }
        }

        // calculate start and end item indexes
        var startIndex = (currentPage - 1) * pageSize;
        var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

        // create an array of pages to ng-repeat in the pager control
        var pages = _.range(startPage, endPage + 1);

        // return object with all pager properties required by the view
        return {
            totalItems: totalItems,
            currentPage: currentPage,
            pageSize: pageSize,
            totalPages: totalPages,
            startPage: startPage,
            endPage: endPage,
            startIndex: startIndex,
            endIndex: endIndex,
            pages: pages
        };
    };

    Pagination.prototype.render = function render() {
        var _this2 = this;

        var pager = this.state.pager;

        if (!pager.pages || pager.pages.length <= 1) {
            // don't display pager if there is only 1 page
            return null;
        }

        return React.createElement(
            'ul',
            { className: 'pagination' },
            React.createElement(
                'li',
                { className: pager.currentPage === 1 ? 'disabled' : '' },
                React.createElement(
                    'a',
                    { onClick: function onClick() {
                        return _this2.setPage(1);
                    } },
                    'First'
                )
            ),
            React.createElement(
                'li',
                { className: pager.currentPage === 1 ? 'disabled' : '' },
                React.createElement(
                    'a',
                    { onClick: function onClick() {
                        return _this2.setPage(pager.currentPage - 1);
                    } },
                    'Previous'
                )
            ),
            pager.pages.map(function (page, index) {
                return React.createElement(
                    'li',
                    { key: index, className: pager.currentPage === page ? 'active' : '' },
                    React.createElement(
                        'a',
                        { onClick: function onClick() {
                            return _this2.setPage(page);
                        } },
                        page
                    )
                );
            }),
            React.createElement(
                'li',
                { className: pager.currentPage === pager.totalPages ? 'disabled' : '' },
                React.createElement(
                    'a',
                    { onClick: function onClick() {
                        return _this2.setPage(pager.currentPage + 1);
                    } },
                    'Next'
                )
            ),
            React.createElement(
                'li',
                { className: pager.currentPage === pager.totalPages ? 'disabled' : '' },
                React.createElement(
                    'a',
                    { onClick: function onClick() {
                        return _this2.setPage(pager.totalPages);
                    } },
                    'Last'
                )
            )
        );
    };

    return Pagination;
}(React.Component);

Pagination.propTypes = propTypes;
Pagination.defaultProps = defaultProps;

/* App Component
-------------------------------------------------*/

var App = function (_React$Component2) {
    _inherits(App, _React$Component2);

    function App() {
        _classCallCheck(this, App);

        // an example array of items to be paged

        var _this3 = _possibleConstructorReturn(this, _React$Component2.call(this));

        var exampleItems = _.range(1, 151).map(function (i) {
            return { id: i, name: 'Item ' + i };
        });

        _this3.state = {
            exampleItems: exampleItems,
            pageOfItems: []
        };

        // bind function in constructor instead of render (https://github.com/yannickcr/eslint-plugin-react/blob/master/docs/rules/jsx-no-bind.md)
        _this3.onChangePage = _this3.onChangePage.bind(_this3);
        return _this3;
    }

    App.prototype.onChangePage = function onChangePage(pageOfItems) {
        // update state with new page of items
        this.setState({ pageOfItems: pageOfItems });
    };

    App.prototype.render = function render() {
        return React.createElement(
            'div',
            null,
            React.createElement(
                'div',
                { className: 'container' },
                React.createElement(
                    'div',
                    { className: 'text-center' },
                    React.createElement(
                        'h1',
                        null,
                        'React - Pagination Example with logic like Google'
                    ),
                    this.state.pageOfItems.map(function (item) {
                        return React.createElement(
                            'div',
                            { key: item.id },
                            item.name
                        );
                    }),
                    React.createElement(Pagination, { items: this.state.exampleItems, onChangePage: this.onChangePage })
                )
            ),
            React.createElement('hr', null),
            React.createElement(
                'div',
                { className: 'credits text-center' },
                React.createElement(
                    'p',
                    null,
                    React.createElement(
                        'a',
                        { href: 'http://jasonwatmore.com/post/2017/03/14/react-pagination-example-with-logic-like-google', target: '_top' },
                        'React - Pagination Example with Logic like Google'
                    )
                ),
                React.createElement(
                    'p',
                    null,
                    React.createElement(
                        'a',
                        { href: 'http://jasonwatmore.com', target: '_top' },
                        'JasonWatmore.com'
                    )
                )
            )
        );
    };

    return App;
}(React.Component);

/* Render Call
-------------------------------------------------*/

ReactDOM.render(React.createElement(App, null), document.getElementById('app'));
